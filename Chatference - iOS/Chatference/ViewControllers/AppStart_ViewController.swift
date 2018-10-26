//
//  AppStart_ViewController.swift
//  Chatference
//
//  Created by James Francis on 2018/10/25.
//  Copyright © 2018 Ryno Claassen. All rights reserved.
//

import UIKit

class AppStart_ViewController: UIViewController {

    @IBOutlet weak var logoImageView: UIImageView!
    @IBOutlet weak var logoLabel: UILabel!

    private let delayTime: Int = 2

    override func viewDidLoad() {
        super.viewDidLoad()

        logoImageView.alpha = 0
        logoLabel.alpha = 0
    }

    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)

        animateLogo()

        DispatchQueue.main.asyncAfter(deadline: .now() + .seconds(delayTime)) {
            self.showLogin()
        }
    }

    func animateLogo() {
        UIView.animate(withDuration: 1) {
            self.logoLabel.alpha = 1
            self.logoImageView.alpha = 1
        }
    }

    func showLogin() {
        performSegue(withIdentifier: "segue_Login_ViewController", sender: nil)
    }
}
