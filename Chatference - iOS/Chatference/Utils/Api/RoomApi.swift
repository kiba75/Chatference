//
//  RoomApi.swift
//  Chatference
//
//  Created by Ryno Claassen on 2018/10/25.
//  Copyright © 2018 Ryno Claassen. All rights reserved.
//

import Foundation
import FirebaseDatabase

class RoomApi: ConnectionApi {
    
    func createRoom(code: String, name: String, completion: @escaping () -> Void) {

        let room: [String: Any] = ["code": code, "name": name, "state": 1]
        
        getDataBaseReference().child("Room").childByAutoId().setValue(room) { (nil, dbref) in
            completion()
        }
    }
    
    func getRoom(code: String, completion: @escaping (Room) -> Void) {
        
        getDataBaseReference().child("Room").queryOrdered(byChild: "code").queryEqual(toValue: code).observeSingleEvent(of: .value) { (snapshot) in
            
            guard let dict = snapshot.value as? [String:Any] else { return }
            self.getDataBaseReference().child("Room").child(dict.first!.key).observeSingleEvent(of: .value, with: { (snapshot) in
                
                if snapshot.exists() {
                    let room = Room(uuid: dict.first!.key, snapshot: snapshot)
                    completion(room!)
                } else {
                    print("we don't have that, add it to the DB now")
                }
            })
        }
    }
}
