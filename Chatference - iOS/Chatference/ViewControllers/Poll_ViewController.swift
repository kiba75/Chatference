//
//  Poll_ViewController.swift
//  Chatference
//
//  Created by James Francis on 2018/10/25.
//  Copyright © 2018 Ryno Claassen. All rights reserved.
//

import UIKit

class Poll_ViewController: UIViewController {

    @IBOutlet weak var contentView: UIView!

    @IBOutlet weak var topConstraint: NSLayoutConstraint!
    @IBOutlet weak var bottomConstraint: NSLayoutConstraint!

    override func viewDidLoad() {
        super.viewDidLoad()

        setupView()
    }

    //MARK: - Actions
    @IBAction func somethingPressed() {

    }

    //MARK: - View Setup
    private func setupView() {

        contentView.alpha = 0
        contentView.layer.cornerRadius = 30
        view.layer.cornerRadius = 30
        view.layer.masksToBounds = true
    }

    func animateIn() {

        contentView.alpha = 1
        topConstraint.constant = -contentView.frame.height
        bottomConstraint.constant = contentView.frame.height
        view.layoutIfNeeded()

        UIView.animate(withDuration: 1.5) {
            self.topConstraint.constant = 0
            self.bottomConstraint.constant = 0
            self.view.layoutIfNeeded()
        }
    }
}
