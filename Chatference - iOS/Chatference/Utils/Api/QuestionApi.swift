//
//  CommentApi.swift
//  Chatference
//
//  Created by Ryno Claassen on 2018/10/25.
//  Copyright © 2018 Ryno Claassen. All rights reserved.
//

import Foundation

class QuestionApi: ConnectionApi {
    // type = 0 comment
    func postQuestion(room: Room, question: String, type: Int, completion: @escaping () -> Void) {
        
        let question: [String: Any] = ["roomUuid": room.uuid, "question": question, "votes": 0, "type": 0, "state": 1]
        
        getDataBaseReference().child("Question").childByAutoId().setValue(question) { (nil, dbref) in
            completion()
        }
    }
    
    // Get questions and observe
    func getQuestions(room: Room, observe: @escaping (Question) -> Void) {
        
        getDataBaseReference().child("Question").queryOrdered(byChild: "roomUuid").queryEqual(toValue: room.uuid).observe(.childAdded) { (snapshot) in
            
            if snapshot.exists() {
                let question = Question(snapshot: snapshot)
                observe(question!)
            }
        }
    }
}
