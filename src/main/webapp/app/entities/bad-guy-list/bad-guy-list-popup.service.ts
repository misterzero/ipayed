import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { BadGuyList } from './bad-guy-list.model';
import { BadGuyListService } from './bad-guy-list.service';

@Injectable()
export class BadGuyListPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private badGuyListService: BadGuyListService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.badGuyListService.find(id)
                    .subscribe((badGuyListResponse: HttpResponse<BadGuyList>) => {
                        const badGuyList: BadGuyList = badGuyListResponse.body;
                        this.ngbModalRef = this.badGuyListModalRef(component, badGuyList);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.badGuyListModalRef(component, new BadGuyList());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    badGuyListModalRef(component: Component, badGuyList: BadGuyList): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.badGuyList = badGuyList;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
