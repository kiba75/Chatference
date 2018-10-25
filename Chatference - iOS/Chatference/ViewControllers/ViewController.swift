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
        // Do any additional setup after loading the view, typically from a nib.
        
//        RoomApi().createRoom(code: "RoomCode", name: "RynoRoom", completion: {
//            print("Successfully created")
//        })
        
        RoomApi().getRoom(code: "RoomCode", completion: {
            print("Successfully got room")
        })
        
//        RoomApi().readRoom()
        
    }
}

