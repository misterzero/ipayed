/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { IpayedTestModule } from '../../../test.module';
import { ProspectDetailComponent } from '../../../../../../main/webapp/app/entities/prospect/prospect-detail.component';
import { ProspectService } from '../../../../../../main/webapp/app/entities/prospect/prospect.service';
import { Prospect } from '../../../../../../main/webapp/app/entities/prospect/prospect.model';

describe('Component Tests', () => {

    describe('Prospect Management Detail Component', () => {
        let comp: ProspectDetailComponent;
        let fixture: ComponentFixture<ProspectDetailComponent>;
        let service: ProspectService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IpayedTestModule],
                declarations: [ProspectDetailComponent],
                providers: [
                    ProspectService
                ]
            })
            .overrideTemplate(ProspectDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProspectDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProspectService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Prospect(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.prospect).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
