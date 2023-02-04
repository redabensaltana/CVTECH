import { TestBed } from '@angular/core/testing';

import { AppInterceptorInterceptor } from './app-interceptor.interceptor';

describe('AppInterceptorInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      AppInterceptorInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: AppInterceptorInterceptor = TestBed.inject(AppInterceptorInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
