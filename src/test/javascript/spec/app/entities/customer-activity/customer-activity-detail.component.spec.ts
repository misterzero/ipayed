/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { IpayedTestModule } from '../../../test.module';
import { CustomerActivityDetailComponent } from '../../../../../../main/webapp/app/entities/customer-activity/customer-activity-detail.component';
import { CustomerActivityService } from '../../../../../../main/webapp/app/entities/customer-activity/customer-activity.service';
import { CustomerActivity } from '../../../../../../main/webapp/app/entities/customer-activity/customer-activity.model';

describe('Component Tests', () => {

    describe('CustomerActivity Management Detail Component', () => {
        let comp: CustomerActivityDetailComponent;
        let fixture: ComponentFixture<CustomerActivityDetailComponent>;
        let service: CustomerActivityService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IpayedTestModule],
                declarations: [CustomerActivityDetailComponent],
                providers: [
                    CustomerActivityService
                ]
            })
            .overrideTemplate(CustomerActivityDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CustomerActivityDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CustomerActivityService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new CustomerActivity(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.customerActivity).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
