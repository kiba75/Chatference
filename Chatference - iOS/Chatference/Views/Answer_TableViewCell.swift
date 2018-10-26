//
//  Answer_TableViewCell.swift
//  Chatference
//
//  Created by James Francis on 2018/10/25.
//  Copyright © 2018 Ryno Claassen. All rights reserved.
//

import Foundation
import UIKit

class Answer_TableViewCell: UITableViewCell {

    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var answerLabel: UILabel!

    //@IBOutlet weak var voteButton: UIButton!
    @IBOutlet weak var voteLabel: UILabel!

    func setupCell(_ question: Comment) {
        // Upcoming functionality
        nameLabel.text = ""

        answerLabel.text = question.question
        voteLabel.text = String(question.votes)
    }
}
