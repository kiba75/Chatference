//
//  Login_ViewController.swift
//  Chatference
//
//  Created by James Francis on 2018/10/25.
//  Copyright © 2018 Ryno Claassen. All rights reserved.
//

import UIKit

class Login_ViewController: UIViewController {

    enum mode {
        case join
        case create
    }
    
    @IBOutlet weak var sessionTextField: UITextField!
    @IBOutlet weak var joinButton: UIButton!
    @IBOutlet weak var createButton: UIButton!

    var mode: mode = .join
    
    override func viewDidLoad() {
        super.viewDidLoad()

        setupView()
    }

    //MARK: - Actions
    @IBAction func joinPressed() {

        sessionTextField.resignFirstResponder()
        
        switch self.mode {
        case .join:
            RoomApi().getRoom(code: sessionTextField.text!) { (room) in
                SessionService.shared.room = room
                self.performSegue(withIdentifier: "segue_Home_ViewController", sender: nil)
            }
            
        case .create:
            RoomApi().createRoom(code: sessionTextField.text!, name: "MakersDay 2018") {
                let alert = UIAlertController(title: "Success", message: "Room created. Please join with provided code.", preferredStyle: UIAlertController.Style.alert)
                alert.addAction(UIAlertAction(title: "Thanks", style: UIAlertAction.Style.default, handler: nil))
                self.present(alert, animated: true, completion: nil)
            }
        }
    }
    
    @IBAction func pressedChangeMode(_ sender: Any) {
        if self.mode == .create {
            self.mode = .join
            self.joinButton.setTitle("Join", for: .normal)
            self.createButton.setTitle("Create room", for: .normal)
            return
        }
        self.mode = .create
        self.joinButton.setTitle("Create", for: .normal)
        self.createButton.setTitle("Join room", for: .normal)
    }

    //MARK: - View Setup
    private func setupView() {

        sessionTextField.layer.cornerRadius = sessionTextField.frame.height / 2
        sessionTextField.layer.masksToBounds = true
        
        joinButton.layer.cornerRadius = joinButton.frame.height / 2
    }
}
