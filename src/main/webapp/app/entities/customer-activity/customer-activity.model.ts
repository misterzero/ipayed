import { BaseEntity } from './../../shared';

export class CustomerActivity implements BaseEntity {
    constructor(
        public id?: number,
        public ipAddress?: string,
        public lastLoginDate?: string,
        public sessionLengthSeconds?: string,
        public customer?: BaseEntity,
    ) {
    }
}
