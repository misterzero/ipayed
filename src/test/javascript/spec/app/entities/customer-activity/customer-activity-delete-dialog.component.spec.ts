/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { IpayedTestModule } from '../../../test.module';
import { CustomerActivityDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/customer-activity/customer-activity-delete-dialog.component';
import { CustomerActivityService } from '../../../../../../main/webapp/app/entities/customer-activity/customer-activity.service';

describe('Component Tests', () => {

    describe('CustomerActivity Management Delete Component', () => {
        let comp: CustomerActivityDeleteDialogComponent;
        let fixture: ComponentFixture<CustomerActivityDeleteDialogComponent>;
        let service: CustomerActivityService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IpayedTestModule],
                declarations: [CustomerActivityDeleteDialogComponent],
                providers: [
                    CustomerActivityService
                ]
            })
            .overrideTemplate(CustomerActivityDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CustomerActivityDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CustomerActivityService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
