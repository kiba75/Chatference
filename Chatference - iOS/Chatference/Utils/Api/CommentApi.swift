//
//  CommentApi.swift
//  Chatference
//
//  Created by Ryno Claassen on 2018/10/25.
//  Copyright © 2018 Ryno Claassen. All rights reserved.
//

import Foundation

class CommentApi: ConnectionApi {

    func postComment(room: Room, comment: String, completion: @escaping () -> Void) {
        
        let question: [String: Any] = ["roomUuid": room.uuid, "comment": comment, "votes": 0, "state": 1]
        
        getDataBaseReference().child("Comment").childByAutoId().setValue(question) { (nil, dbref) in
            completion()
        }
    }
    
    // Get questions and observe
    func getComments(room: Room, observe: @escaping (Comment) -> Void) {
        
        getDataBaseReference().child("Comment").queryOrdered(byChild: "roomUuid").queryEqual(toValue: room.uuid).observe(.childAdded) { (snapshot) in
            
            if snapshot.exists() {
                let question = Comment(snapshot: snapshot)
                observe(question!)
            }
        }
    }
    
    func listenForUpdates(room: Room, observe: @escaping (Comment) -> Void) {
    
        getDataBaseReference().child("Comment").queryOrdered(byChild: "roomUuid").queryEqual(toValue: room.uuid).observe(.childChanged) { (snapshot) in
            
            if snapshot.exists() {
                let question = Comment(snapshot: snapshot)
                observe(question!)
            }
        }
    }
}
