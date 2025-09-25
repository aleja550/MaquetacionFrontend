import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlarmaMatematica } from './alarma-matematica';

describe('AlarmaMatematica', () => {
  let component: AlarmaMatematica;
  let fixture: ComponentFixture<AlarmaMatematica>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AlarmaMatematica]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AlarmaMatematica);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
