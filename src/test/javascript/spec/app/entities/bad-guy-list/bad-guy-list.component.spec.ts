/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IpayedTestModule } from '../../../test.module';
import { BadGuyListComponent } from '../../../../../../main/webapp/app/entities/bad-guy-list/bad-guy-list.component';
import { BadGuyListService } from '../../../../../../main/webapp/app/entities/bad-guy-list/bad-guy-list.service';
import { BadGuyList } from '../../../../../../main/webapp/app/entities/bad-guy-list/bad-guy-list.model';

describe('Component Tests', () => {

    describe('BadGuyList Management Component', () => {
        let comp: BadGuyListComponent;
        let fixture: ComponentFixture<BadGuyListComponent>;
        let service: BadGuyListService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IpayedTestModule],
                declarations: [BadGuyListComponent],
                providers: [
                    BadGuyListService
                ]
            })
            .overrideTemplate(BadGuyListComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BadGuyListComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BadGuyListService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new BadGuyList(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.badGuyLists[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
