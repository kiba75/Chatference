//
//  RoomApi.swift
//  Chatference
//
//  Created by Ryno Claassen on 2018/10/25.
//  Copyright © 2018 Ryno Claassen. All rights reserved.
//

import Foundation

class RoomApi: ConnectionApi {
    
    typealias Completion = () -> ()
    
    func createRoom(code: String, name: String, completion: @escaping Completion) {
        
        let room: [String: Any] = ["code": code, "name": name, "state": 1]
        
        let dbref = getDataBaseReference()
        dbref.child("Room").childByAutoId().setValue(room) { (nil, dbref) in
            completion()
        }
    }
    
    func getRoom(code: String, completion: Completion) {
        getDataBaseReference().child("Room").queryOrdered(byChild: "code").queryEqual(toValue: code).observe(.value, with: { (snapshot) in
            if snapshot.exists() {
                print("we have that artist, the id is \(snapshot)")
                
                for a in ((snapshot.value as AnyObject).allKeys)!{
                    print(a)
                }
                
            } else {
                print("we don't have that, add it to the DB now")
            }
        })
    }
    
    func readRoom() {
        getDataBaseReference().child("Room").observe(.childAdded, with: { (data) in
            print(data)
        })
    }
}
