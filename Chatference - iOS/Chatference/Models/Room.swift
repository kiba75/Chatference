//
//  Room.swift
//  Chatference
//
//  Created by Ryno Claassen on 2018/10/25.
//  Copyright © 2018 Ryno Claassen. All rights reserved.
//

import Foundation
import FirebaseDatabase

class Room {
    let uuid: String
    let code: String
    let name: String
    let state: Int
    
    init(uuid: String, code: String, name: String, state: Int) {
        self.uuid = uuid
        self.code = code
        self.name = name
        self.state = state
    }
    
    init?(uuid: String, snapshot: DataSnapshot) {
        guard let dict = snapshot.value as? [String:Any] else { return nil }
        guard let code = dict["code"]  as? String else { return nil }
        guard let name = dict["name"]  as? String else { return nil }
        guard let state  = dict["state"] as? Int  else { return nil }
        
        self.uuid = uuid
        self.code = code
        self.name = name
        self.state = state
    }
}
