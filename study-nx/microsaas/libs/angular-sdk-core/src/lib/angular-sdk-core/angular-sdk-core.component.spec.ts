import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AngularSdkCoreComponent } from './angular-sdk-core.component';

describe('AngularSdkCoreComponent', () => {
  let component: AngularSdkCoreComponent;
  let fixture: ComponentFixture<AngularSdkCoreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AngularSdkCoreComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(AngularSdkCoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
