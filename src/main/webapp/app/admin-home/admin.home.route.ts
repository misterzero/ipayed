import { Route } from '@angular/router';

import { AdminHomeComponent } from './';

export const ADMIN_HOME_ROUTE: Route = {
    path: '',
    component: AdminHomeComponent,
    data: {
        authorities: [ 'ROLE_ADMIN' ],
    }
};

