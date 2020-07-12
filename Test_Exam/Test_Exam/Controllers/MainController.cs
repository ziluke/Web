using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Test_Exam.Models;
using Test_Exam.DataAbstractionLayer;

namespace Test_Exam.Controllers
{
    public class MainController : Controller
    {
        // GET: Main
        public ActionResult Index()
        {
            return View("FilterNews");
        }

        public string GetStudentsFromGroup()
        {
            int group_id = int.Parse(Request.Params["group_id"]);
            DAL dal = new DAL();
            List<Student> slist = dal.GetStudentsFromGroup(group_id);
            ViewData["studentList"] = slist;

            string result = "<table><thead><th>Id</th><th>Nume</th><th>Password</th><th>Group_Id</th></thead>";

            foreach (Student stud in slist)
            {
                result += "<tr><td>" + stud.Id + "</td><td>" + stud.Nume + "</td><td>" + stud.Password + "</td><td>" + stud.Group_id + "</td><td></tr>";
            }

            result += "</table>";
            return result;
        }
    }
}