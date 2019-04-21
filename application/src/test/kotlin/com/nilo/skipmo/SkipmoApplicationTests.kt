package com.nilo.skipmo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

// needed, if you use the $-invocations

//@ExtendWith(SpringExtension::class)
//@ContextConfiguration(loader= AnnotationConfigContextLoader::class)
//@EnableMapRepositories("com.nilo.skipmo.repositories")
class SkipmoApplicationTests {

//    @Autowired
//    lateinit var gameRepository: GameRepository
//
//    @Autowired
//    lateinit var userRepository: UserRepository
//
//    @Autowired
//    lateinit var userGameRepository: UserGameRepository

    @Test
    fun test() {

        val game1 = UUID.randomUUID()
        val game2 = UUID.randomUUID()
        val user1 = UUID.randomUUID()
        val user2 = UUID.randomUUID()


//        println(gameRepository.toString())
//        val id = UUID.randomUUID()
//        val nickId = UUID.randomUUID()
//        val game = Game("Nick", "Lori", id= id)
//
//        gameRepository.save( game )
//        val nickish = userRepository.findById( nickId )
//        userRepository.save( nickish.orElse( User("Nick", nickId, listOf(id)) ) )
//
//        println ( userRepository.findById( nickId ).map{ it.name } )


//        keyValueOperations.


    }


}

