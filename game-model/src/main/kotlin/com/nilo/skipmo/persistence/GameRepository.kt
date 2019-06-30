package com.nilo.skipmo.persistence

import com.nilo.skipmo.domain.game.Game
import org.springframework.data.keyvalue.repository.KeyValueRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
internal interface GameRepository : KeyValueRepository<Game, UUID>

//@Repository
//internal interface UserRepository : KeyValueRepository<User, UUID>

@Repository
internal interface UserGameRepository : KeyValueRepository<UUID, UUID>

@Repository
internal interface UserNameId : KeyValueRepository<UUID, String>
