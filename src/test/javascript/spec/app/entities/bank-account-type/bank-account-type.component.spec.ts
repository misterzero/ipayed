/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IpayedTestModule } from '../../../test.module';
import { BankAccountTypeComponent } from '../../../../../../main/webapp/app/entities/bank-account-type/bank-account-type.component';
import { BankAccountTypeService } from '../../../../../../main/webapp/app/entities/bank-account-type/bank-account-type.service';
import { BankAccountType } from '../../../../../../main/webapp/app/entities/bank-account-type/bank-account-type.model';

describe('Component Tests', () => {

    describe('BankAccountType Management Component', () => {
        let comp: BankAccountTypeComponent;
        let fixture: ComponentFixture<BankAccountTypeComponent>;
        let service: BankAccountTypeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IpayedTestModule],
                declarations: [BankAccountTypeComponent],
                providers: [
                    BankAccountTypeService
                ]
            })
            .overrideTemplate(BankAccountTypeComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BankAccountTypeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BankAccountTypeService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new BankAccountType(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.bankAccountTypes[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
