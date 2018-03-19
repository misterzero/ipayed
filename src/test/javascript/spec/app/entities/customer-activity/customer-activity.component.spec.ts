/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IpayedTestModule } from '../../../test.module';
import { CustomerActivityComponent } from '../../../../../../main/webapp/app/entities/customer-activity/customer-activity.component';
import { CustomerActivityService } from '../../../../../../main/webapp/app/entities/customer-activity/customer-activity.service';
import { CustomerActivity } from '../../../../../../main/webapp/app/entities/customer-activity/customer-activity.model';

describe('Component Tests', () => {

    describe('CustomerActivity Management Component', () => {
        let comp: CustomerActivityComponent;
        let fixture: ComponentFixture<CustomerActivityComponent>;
        let service: CustomerActivityService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IpayedTestModule],
                declarations: [CustomerActivityComponent],
                providers: [
                    CustomerActivityService
                ]
            })
            .overrideTemplate(CustomerActivityComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CustomerActivityComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CustomerActivityService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new CustomerActivity(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.customerActivities[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
