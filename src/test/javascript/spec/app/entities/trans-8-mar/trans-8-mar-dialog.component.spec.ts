/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { IpayedTestModule } from '../../../test.module';
import { Trans8MarDialogComponent } from '../../../../../../main/webapp/app/entities/trans-8-mar/trans-8-mar-dialog.component';
import { Trans8MarService } from '../../../../../../main/webapp/app/entities/trans-8-mar/trans-8-mar.service';
import { Trans8Mar } from '../../../../../../main/webapp/app/entities/trans-8-mar/trans-8-mar.model';
import { BankAccountService } from '../../../../../../main/webapp/app/entities/bank-account';
import { CustomerService } from '../../../../../../main/webapp/app/entities/customer';
import { TransactionTypeService } from '../../../../../../main/webapp/app/entities/transaction-type';

describe('Component Tests', () => {

    describe('Trans8Mar Management Dialog Component', () => {
        let comp: Trans8MarDialogComponent;
        let fixture: ComponentFixture<Trans8MarDialogComponent>;
        let service: Trans8MarService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IpayedTestModule],
                declarations: [Trans8MarDialogComponent],
                providers: [
                    BankAccountService,
                    CustomerService,
                    TransactionTypeService,
                    Trans8MarService
                ]
            })
            .overrideTemplate(Trans8MarDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(Trans8MarDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(Trans8MarService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Trans8Mar(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.trans8Mar = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'trans8MarListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Trans8Mar();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.trans8Mar = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'trans8MarListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
