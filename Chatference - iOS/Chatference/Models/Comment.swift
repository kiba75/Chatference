//
//  Question.swift
//  Chatference
//
//  Created by Ryno Claassen on 2018/10/25.
//  Copyright © 2018 Ryno Claassen. All rights reserved.
//

import Foundation
import FirebaseDatabase

class Comment {
    let questionUuid: String
    let question: String
    let roomUuid: String
    let state: Int
    let votes: Int
    
    init(questionUuid: String, question: String, roomUuid: String, state: Int, votes: Int) {
        self.questionUuid = questionUuid
        self.question = question
        self.roomUuid = roomUuid
        self.state = state
        self.votes = votes
    }
    
    init?(snapshot: DataSnapshot) {
        guard let dict = snapshot.value as? [String:Any] else { return nil }
        guard let question = dict["comment"]  as? String else { return nil }
        guard let roomUuid = dict["roomUuid"]  as? String else { return nil }
        guard let state  = dict["state"] as? Int  else { return nil }
        guard let votes  = dict["votes"] as? Int  else { return nil }
        
        self.questionUuid = snapshot.key
        self.question = question
        self.roomUuid = roomUuid
        self.state = state
        self.votes = votes
    }
}
