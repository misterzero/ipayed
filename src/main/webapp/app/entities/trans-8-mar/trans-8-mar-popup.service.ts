import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { Trans8Mar } from './trans-8-mar.model';
import { Trans8MarService } from './trans-8-mar.service';

@Injectable()
export class Trans8MarPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private trans8MarService: Trans8MarService

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
                this.trans8MarService.find(id)
                    .subscribe((trans8MarResponse: HttpResponse<Trans8Mar>) => {
                        const trans8Mar: Trans8Mar = trans8MarResponse.body;
                        this.ngbModalRef = this.trans8MarModalRef(component, trans8Mar);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.trans8MarModalRef(component, new Trans8Mar());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    trans8MarModalRef(component: Component, trans8Mar: Trans8Mar): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.trans8Mar = trans8Mar;
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
