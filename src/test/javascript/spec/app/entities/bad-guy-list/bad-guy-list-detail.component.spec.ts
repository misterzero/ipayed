/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { IpayedTestModule } from '../../../test.module';
import { BadGuyListDetailComponent } from '../../../../../../main/webapp/app/entities/bad-guy-list/bad-guy-list-detail.component';
import { BadGuyListService } from '../../../../../../main/webapp/app/entities/bad-guy-list/bad-guy-list.service';
import { BadGuyList } from '../../../../../../main/webapp/app/entities/bad-guy-list/bad-guy-list.model';

describe('Component Tests', () => {

    describe('BadGuyList Management Detail Component', () => {
        let comp: BadGuyListDetailComponent;
        let fixture: ComponentFixture<BadGuyListDetailComponent>;
        let service: BadGuyListService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IpayedTestModule],
                declarations: [BadGuyListDetailComponent],
                providers: [
                    BadGuyListService
                ]
            })
            .overrideTemplate(BadGuyListDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BadGuyListDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BadGuyListService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new BadGuyList(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.badGuyList).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
