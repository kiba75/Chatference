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

    override func viewDidLoad() {
        super.viewDidLoad()

        // This will cause the tableview to autolayout the height for the cell, as the content is dynamic
        tableView.estimatedRowHeight = 50.0
        tableView.rowHeight = UITableView.automaticDimension
    }

}

//MARK: - TableView Datasource
extension Answer_TableViewController {

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {

         let cell: Answer_TableViewCell = tableView.dequeueReusableCell(withIdentifier: "AnswerCellIdentifier") as! Answer_TableViewCell

        return cell
    }
}

//MARK: - TableView Delegate
extension Answer_TableViewController {

    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {

    }
}

