//
//  Login_ViewController.swift
//  Chatference
//
//  Created by James Francis on 2018/10/25.
//  Copyright © 2018 Ryno Claassen. All rights reserved.
//

import UIKit

class Login_ViewController: UIViewController {

    @IBOutlet weak var sessionTextField: UITextField!
    @IBOutlet weak var joinButton: UIButton!
    @IBOutlet weak var createButton: UIButton!

    override func viewDidLoad() {
        super.viewDidLoad()

        setupView()
    }

    //MARK: - Actions
    @IBAction func joinPressed() {
        RoomApi().getRoom(code: sessionTextField.text!) { (room) in
            SessionService.shared.room = room
            self.performSegue(withIdentifier: "segue_Home_ViewController", sender: nil)
        }
    }

    @IBAction func createPressed() {
    }

    //MARK: - View Setup
    private func setupView() {

        sessionTextField.layer.cornerRadius = sessionTextField.frame.height / 2
        sessionTextField.layer.masksToBounds = true
        
        joinButton.layer.cornerRadius = joinButton.frame.height / 2
    }
}
