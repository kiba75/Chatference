//
//  Answer_TableViewController.swift
//  Chatference
//
//  Created by James Francis on 2018/10/25.
//  Copyright © 2018 Ryno Claassen. All rights reserved.
//

import Foundation
import UIKit

class Answer_TableViewController: UITableViewController {

    @IBOutlet weak var answersTableView: UITableView!

    var comments: [Comment] = [Comment(question: "Initial question", roomUuid: SessionService.shared.room!.uuid, state: 1, votes: 5)]
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // This will cause the tableview to autolayout the height for the cell, as the content is dynamic
        tableView.estimatedRowHeight = 50.0
        tableView.rowHeight = UITableView.automaticDimension
        
        CommentApi().getComments(room: SessionService.shared.room!) { (comment) in
            self.comments.append(comment)
            self.tableView.reloadData()
        }
    }
}

//MARK: - TableView Datasource
extension Answer_TableViewController {

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return comments.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {

         let cell: Answer_TableViewCell = tableView.dequeueReusableCell(withIdentifier: "AnswerCellIdentifier") as! Answer_TableViewCell

        cell.setupCell(comments[indexPath.row])
        return cell
    }
}

//MARK: - TableView Delegate
extension Answer_TableViewController {

    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {

    }
}

