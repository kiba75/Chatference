//
//  JoinRoom.swift
//  Chatference
//
//  Created by Ryno Claassen on 2018/10/25.
//  Copyright © 2018 Ryno Claassen. All rights reserved.
//

import Foundation
import FirebaseDatabase

class ConnectionApi {
    func getDataBaseReference() -> DatabaseReference {
        return Database.database().reference()
    }
}
