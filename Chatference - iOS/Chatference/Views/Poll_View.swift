//
//  PollView.swift
//  Chatference
//
//  Created by James Francis on 2018/10/25.
//  Copyright © 2018 Ryno Claassen. All rights reserved.
//

import UIKit

class Poll_View: UIView {

    @IBOutlet weak var roundedView: UIView!

    override init(frame: CGRect) {
        super.init(frame: frame)
    }

    required init(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)!
    }

    //MARK: - View Setups
    func setupView() {

        roundedView.layer.cornerRadius = roundedView.frame.height / 2
    }
}

