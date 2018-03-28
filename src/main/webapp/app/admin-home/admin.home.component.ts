import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { Router } from '@angular/router';

import { Account, LoginModalService, Principal } from '../shared';

@Component({
    selector: 'admin-home',
    templateUrl: 'admin.home.component.html',
    styleUrls: [
        'admin.home.component.css'
    ]

})
export class AdminHomeComponent implements OnInit {
    constructor(){

    }

    ngOnInit(){

    }
}
