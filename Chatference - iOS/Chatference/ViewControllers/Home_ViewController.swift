//
//  Home_ViewController.swift
//  Chatference
//
//  Created by James Francis on 2018/10/25.
//  Copyright © 2018 Ryno Claassen. All rights reserved.
//

import UIKit

class Home_ViewController: UIViewController {

    @IBOutlet weak var pollView: Poll_View!

    weak var answersTableViewController: Answer_TableViewController?
    //weak var pollViewController: Poll_ViewController?

    @IBOutlet weak var commentTextField: UITextField!

    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationItem.title = SessionService.shared.room?.name
        setupView()
        
        CommentApi().postComment(room: SessionService.shared.room! , comment: "HELLO THIS IS A COMMENT") {
            // fsfsd
        }
    }

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "embeded_AnswersTableViewController" {
            answersTableViewController = segue.destination as? Answer_TableViewController
            return
        }

//        if segue.identifier == "embeded_PollViewController" {
//            pollViewController = segue.destination as? Poll_ViewController
//            return
//        }
    }

    //MARK: - Actions
    @IBAction func pollPressed() {
        //pollViewController?.animateIn()
    }

    @IBAction func postPressed() {
        let textToPost = commentTextField.text
        postComment(textToPost)
        commentTextField.text = ""
        commentTextField.resignFirstResponder()
    }

    @IBAction func keyboardReturnPressed() {
        postPressed()
    }

    //MARK: - View Setup
    private func setupView() {

        hideNavigationBarBorder()
        
        pollView.setupView()
    }
}

//MARK: - View Stuff
extension Home_ViewController {

    fileprivate func hideNavigationBarBorder() {
        // Remove the silly navigation bar white line at the bottom.
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
}

//MARK: - Posting comments
extension Home_ViewController {

    fileprivate func postComment(_ text: String?) {
        if text == nil || text == "" {
            return
        }

        //api here
    }
}
