/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IpayedTestModule } from '../../../test.module';
import { Trans8MarComponent } from '../../../../../../main/webapp/app/entities/trans-8-mar/trans-8-mar.component';
import { Trans8MarService } from '../../../../../../main/webapp/app/entities/trans-8-mar/trans-8-mar.service';
import { Trans8Mar } from '../../../../../../main/webapp/app/entities/trans-8-mar/trans-8-mar.model';

describe('Component Tests', () => {

    describe('Trans8Mar Management Component', () => {
        let comp: Trans8MarComponent;
        let fixture: ComponentFixture<Trans8MarComponent>;
        let service: Trans8MarService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IpayedTestModule],
                declarations: [Trans8MarComponent],
                providers: [
                    Trans8MarService
                ]
            })
            .overrideTemplate(Trans8MarComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(Trans8MarComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(Trans8MarService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Trans8Mar(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.trans8Mars[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
