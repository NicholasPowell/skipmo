package com.nilo.skipmo.domain.game

internal class Player(
        val name : String,
        private val game: Game,
        val hand : Hand = Hand(),
        val stockPile : StockPile = StockPile(),
        val discardPiles : MutableList<DiscardPile> = mutableListOf(DiscardPile(), DiscardPile(), DiscardPile(), DiscardPile())
) {
    private val rules = PlayerRules(hand, this)

    fun discard(slot : Hand.Slot, discardPile : DiscardPile.Index) {
        rules.youCannotDiscardFromAnEmptyHandSlot(slot)
        discardPiles[discardPile.index].addToTop(hand.play(slot))
        drawToFull(game.deck)
        game.endTurn()
    }

    fun draw( drawFx : ()-> Card) {
        hand.takeCard(drawFx())
    }

    fun playFromStockPile( buildPile : BuildPile) {
        stockPile.play( buildPile, game::winner )
    }

    fun playFromDiscardToBuildPile(discardPileIndex: DiscardPile.Index, buildPile : BuildPile) {
        val discardPile = discardPiles[discardPileIndex.index]
        discardPile.validateBeforePlay()
        buildPile.play(discardPile.showTopCard()) { discardPile.play() }
    }

    fun playFromHandToBuildPile(slot : Hand.Slot, buildPile : BuildPile, deck: Deck) {
        hand.validateBeforePlay(slot) {
            buildPile.play(it) { hand.play(slot) }
        }
        rules.ifYouPlayAllOfYourCardsYouMustDrawANewHand(deck)
    }

    fun drawToFull( deck: Deck) {
        hand.drawToFull(deck)
    }

    fun getConverter() = PlayerConverter(this)
    fun getDisplay() = PlayerDisplay(this)

}
