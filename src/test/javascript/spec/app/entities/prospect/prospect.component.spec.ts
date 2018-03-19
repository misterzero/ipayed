/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IpayedTestModule } from '../../../test.module';
import { ProspectComponent } from '../../../../../../main/webapp/app/entities/prospect/prospect.component';
import { ProspectService } from '../../../../../../main/webapp/app/entities/prospect/prospect.service';
import { Prospect } from '../../../../../../main/webapp/app/entities/prospect/prospect.model';

describe('Component Tests', () => {

    describe('Prospect Management Component', () => {
        let comp: ProspectComponent;
        let fixture: ComponentFixture<ProspectComponent>;
        let service: ProspectService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IpayedTestModule],
                declarations: [ProspectComponent],
                providers: [
                    ProspectService
                ]
            })
            .overrideTemplate(ProspectComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProspectComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProspectService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Prospect(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.prospects[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
