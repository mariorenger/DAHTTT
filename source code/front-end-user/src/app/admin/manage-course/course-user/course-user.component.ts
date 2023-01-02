import {AfterContentInit, AfterViewInit, Component, Input, OnInit, ViewChild} from '@angular/core';
import {UserService} from '../../../_services/user.service';
import {ExamService} from '../../../_services/exam.service';
import {UserAccount} from '../../../models/user-account';
import {PaginationDetail} from '../../../models/pagination/pagination-detail';
import {delay, switchMap} from 'rxjs/operators';
import {ToastrService} from 'ngx-toastr';

import {Course} from '../../../models/course';
@Component({
  selector: 'app-course-user',
  templateUrl: './course-user.component.html',
  styleUrls: ['./course-user.component.scss']
})
export class CourseUserComponent implements OnInit {
  @Input() courseInfo: Course;
  userList: UserAccount[] = [];
  paginationDetail: PaginationDetail;
  skeleton = true;
  course: Course;

  pageOptions: any = [
    {display: 20, num: 20},
    {display: 50, num: 50},
    {display: 100, num: 100},
    {display: 'Tất cả', num: ''},
  ];
  searchKeyWord = '';
  showModalUpdate = false;
  toggle = false;
  pageCountShowing = 3;

  constructor(private examService: ExamService,
              private userService: UserService) { }

  ngOnInit(): void {
    this.course = Object.assign({}, this.courseInfo);
    console.log(this.course.id)
    this.fetchUserList();
  }

  fetchUserList(){
    this.examService.searchUserList(0, 3, this.course.id).subscribe(res => {
      this.userList = res.data;
      this.paginationDetail = res.paginationDetails;
      this.skeleton = false;
      console.log(this.course.id)
      console.table(this.paginationDetail);
    });
  }

  toggleModalDetail() {
    this.showModalUpdate = !this.showModalUpdate;
  }
  closeModal() {
    this.showModalUpdate = false;
    this.toggle = false;
  }

  goPreviousPage() {
    const isFirstPage: boolean = this.paginationDetail.isFirstPage;
    if (!isFirstPage) {
      this.examService.searchUserList(this.paginationDetail.previousPage.pageNumber, this.pageCountShowing, this.course.id)
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
      this.examService.searchUserList(this.paginationDetail.nextPage.pageNumber, this.pageCountShowing, this.course.id
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
  }

}
