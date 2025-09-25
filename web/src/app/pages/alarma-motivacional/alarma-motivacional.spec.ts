import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlarmaMotivacional } from './alarma-motivacional';

describe('AlarmaMotivacional', () => {
  let component: AlarmaMotivacional;
  let fixture: ComponentFixture<AlarmaMotivacional>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AlarmaMotivacional]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AlarmaMotivacional);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
