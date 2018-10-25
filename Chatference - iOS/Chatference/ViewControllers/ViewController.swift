//
//  ViewController.swift
//  Chatference
//
//  Created by Ryno Claassen on 2018/10/25.
//  Copyright © 2018 Ryno Claassen. All rights reserved.
//

import UIKit
import FirebaseDatabase

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        
//        createRoom()
        
        RoomApi().getRoom(code: "1234") { (room) in

            //            self.postQuestion(room: room, question: "STAAAPPHH")
            
            QuestionApi().getQuestions(room: room, observe: { (question) in
                print(question.question)
            })
            
        }
    }
    
    func createRoom() {
        RoomApi().createRoom(code: "1234", name: "Absa PI Planning", completion: {
            print("Successfully created")
        })
    }
    
    func postQuestion(room: Room, question: String) {
        QuestionApi().postQuestion(room: room, question: question, type: 0, completion: {
            print("Successfully post question")
        })
    }
}

