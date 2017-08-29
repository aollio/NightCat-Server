package users

import org.junit.Test

//@RunWith(SpringJUnit4ClassRunner::class)
//@SpringBootTest(classes = arrayOf(Application::class))
class Test {

//    @Autowired
//    lateinit var rep: UserRepository

//    @Test
//    fun find_entity() {
//        println("打算大萨达")
//        println(rep.findAll().size)
//    }

    @Test
    fun call_null() {
        nuller(null)
    }

    fun nuller(int: Int? = 0) {
        println(int)
    }
}