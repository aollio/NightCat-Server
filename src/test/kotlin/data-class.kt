class DataClassTest {

    var one: String
        get() {
            print("get");
            return "one"
        }
        set(value) {
            print("set")
        }

    fun test(name: String) {
        print(name)
    }
}

fun main(args: Array<String>) {
    try{
    DataClassTest().test(null!!)}
    catch (e:Exception){
        print(e.javaClass.canonicalName)
    }
}