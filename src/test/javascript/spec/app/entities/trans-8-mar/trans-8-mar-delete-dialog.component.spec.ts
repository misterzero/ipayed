/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { IpayedTestModule } from '../../../test.module';
import { Trans8MarDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/trans-8-mar/trans-8-mar-delete-dialog.component';
import { Trans8MarService } from '../../../../../../main/webapp/app/entities/trans-8-mar/trans-8-mar.service';

describe('Component Tests', () => {

    describe('Trans8Mar Management Delete Component', () => {
        let comp: Trans8MarDeleteDialogComponent;
        let fixture: ComponentFixture<Trans8MarDeleteDialogComponent>;
        let service: Trans8MarService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IpayedTestModule],
                declarations: [Trans8MarDeleteDialogComponent],
                providers: [
                    Trans8MarService
                ]
            })
            .overrideTemplate(Trans8MarDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(Trans8MarDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(Trans8MarService);
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
