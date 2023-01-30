import {AfterContentInit, AfterViewInit, Component, Input, OnInit, ViewChild} from '@angular/core';
import {UserService} from '../../../_services/user.service';
import {ExamService} from '../../../_services/exam.service';
import {UserAccount} from '../../../models/user-account';
import {PaginationDetail} from '../../../models/pagination/pagination-detail';
import {delay, switchMap} from 'rxjs/operators';
import {ToastrService} from 'ngx-toastr';

import {Exam} from '../../../models/exam';
import {ExamResult} from '../../../models/exam-result';

import {Course} from '../../../models/course';

@Component({
  selector: 'app-add-user-to-test',
  templateUrl: './add-user-to-test.component.html',
  styleUrls: ['./add-user-to-test.component.scss']
})
export class AddUserToTestComponent implements OnInit {

  @Input() examInfo: Exam;
  userList: UserAccount[] = [];
  paginationDetail: PaginationDetail;
  skeleton = true;
  exam: Exam;

  pageOptions: any = [
    {display: 20, num: 20},
    {display: 50, num: 50},
    {display: 100, num: 100},
    {display: 'Tất cả', num: ''},
  ];
  searchKeyWord = '';
  showModalUpdate = false;
  showAdd = true;
  toggle = false;
  pageCountShowing = 3;

  constructor(private examService: ExamService,
              private userService: UserService) { }

  ngOnInit(): void {
    this.exam = Object.assign({}, this.examInfo);
    this.fetchUserList();
  }

  fetchUserList(){
    this.examService.searchAllUser(0, 3, this.exam.id).subscribe(res => {
      this.userList = res.data;
      this.paginationDetail = res.paginationDetails;
      this.skeleton = false;
    });
  }

  toggleModalDetail() {
    this.showModalUpdate = !this.showModalUpdate;
    this.showAdd = !this.showAdd;
  }
  closeModal() {
    this.showModalUpdate = false;
    this.toggle = false;
  }

  goPreviousPage() {
    const isFirstPage: boolean = this.paginationDetail.isFirstPage;
    if (!isFirstPage) {
      this.examService.searchAllUser(this.paginationDetail.previousPage.pageNumber, this.pageCountShowing, this.exam.id)
        .subscribe(res => {
          this.userList = res.data;
          this.paginationDetail = res.paginationDetails;
          console.table(this.userList);
        });
    }

  }

  goNextPage() {
    const isLastPage = !this.paginationDetail.nextPage.available;
    if (!isLastPage) {
      this.examService.searchAllUser(this.paginationDetail.nextPage.pageNumber, this.pageCountShowing, this.exam.id
      ).subscribe(res => {
        this.userList = res.data;
        this.paginationDetail = res.paginationDetails;
        console.table(this.paginationDetail);
      });
    }
  }

  fetchUsersAfterCRUD($event: any) {
    this.userList = $event.data;
    this.paginationDetail = $event.paginationDetails;
  }

  changeDeleted(user: UserAccount) {
    // user.deleted = !user.deleted;
    // this.userService.deleteUser(user.id, user.deleted)
    //   .pipe(switchMap(res => this.userService.getUserList(0, 20)))
    //   .subscribe(res => {
    //     this.toast.success('Đã thay đổi trạng thái tài khoản', 'Thành công');
    //   }, error => {
    //     user.deleted = !user.deleted;
    //     this.toast.error('Không thể thay đổi trạng thái', 'Lỗi');
    //   });
  }

  searchUser(searchKeyWord: string) {
    console.log(searchKeyWord);
    this.userService.searchUserList(0, 20, searchKeyWord).subscribe(res => {
      this.userList = res.data;
      this.paginationDetail = res.paginationDetails;
    });
    this.showAdd = !this.showAdd;
  }

  addToExam(user: UserAccount){
    this.examService.addUserToCourse(this.exam.id, user.id).subscribe(res => {
      console.log("add ok r");
    });
  }

}
