
import Foundation


func foo(a : Int){
    print(a)
}

func testGuard (a : Int){
    guard a == 5 else {
        print("You are right")
        return
    }
    print(a)
}

testGuard(a: 3)
