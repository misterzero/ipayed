/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { IpayedTestModule } from '../../../test.module';
import { BankAccountTypeDetailComponent } from '../../../../../../main/webapp/app/entities/bank-account-type/bank-account-type-detail.component';
import { BankAccountTypeService } from '../../../../../../main/webapp/app/entities/bank-account-type/bank-account-type.service';
import { BankAccountType } from '../../../../../../main/webapp/app/entities/bank-account-type/bank-account-type.model';

describe('Component Tests', () => {

    describe('BankAccountType Management Detail Component', () => {
        let comp: BankAccountTypeDetailComponent;
        let fixture: ComponentFixture<BankAccountTypeDetailComponent>;
        let service: BankAccountTypeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IpayedTestModule],
                declarations: [BankAccountTypeDetailComponent],
                providers: [
                    BankAccountTypeService
                ]
            })
            .overrideTemplate(BankAccountTypeDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BankAccountTypeDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BankAccountTypeService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new BankAccountType(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.bankAccountType).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
