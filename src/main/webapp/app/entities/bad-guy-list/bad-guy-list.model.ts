import { BaseEntity } from './../../shared';

export class BadGuyList implements BaseEntity {
    constructor(
        public id?: number,
        public uniqueRecord?: string,
        public sdnName?: string,
        public sdnType?: string,
        public sanctionsProgramName?: string,
        public titleOfIndividual?: string,
        public vesselCallSign?: string,
        public vesselTonnage?: string,
        public grossRegisteredTonnage?: string,
        public isVessel?: string,
        public vesselOwner?: string,
        public remark?: string,
    ) {
    }
}
