import { Route, Routes } from 'react-router-dom';
import './App.css';
import HomePage from './component/home/HomePage';
import ErrorPage from './component/gen/error/ErrorPage';
import Menu from './component/menu/Menu';
import AddUserPage from './component/user/adduser/AddUserPage';
import UserListPage from './component/user/userlist/UserListPage';
import AddCreditApplicationPage from './component/credit_application/addapplication/AddCreditApplicationPage';
import ApplicationInfo from './component/credit_application/applicationlist/ApplicationInfo';

function App() {
  return (
    <div className="App">
      <Menu></Menu>
     
     <Routes>
       <Route path="/" element={<HomePage></HomePage>}></Route>
       <Route path="*" element={<ErrorPage></ErrorPage>}></Route>
       <Route path="/users/add" element={<AddUserPage />}></Route>
       <Route path="/users/list" element={<UserListPage />}></Route>
       <Route path="/applications/add" element={<AddCreditApplicationPage />}></Route>
       <Route path="/applications/find" element={<ApplicationInfo />}></Route>
     </Routes>
      
    </div>
  );
}

export default App;
