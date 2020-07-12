using NewsService.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace NewsService.Controllers
{
    public class LoginController : Controller
    {

        private UserDbContext db = new UserDbContext();
        // GET: Login
        public ActionResult Index()
        {
            return View();
        }

        [HttpPost]
        public ActionResult authorize(User user)
        {
            List<User> usr = db.User.Where(u => u.Username == user.Username && u.Password == user.Password).ToList();
            
            if(usr.Count() > 0)
            {
                List<User> admin = usr.Where(u => u.Username == "ziluke").ToList();
                if (admin.Count() > 0)
                {
                    Session["admin"] = 1;
                }
                else
                {

                    Session["admin"] = 0;
                }
                Session["userId"] = user.Id;
                return RedirectToAction("Index", "News");
            }
            return RedirectToAction("Index", "Login");
        }

        public ActionResult logOut()
        {
            Session.Abandon();
            return RedirectToAction("Index", "Login");
        }
    }
}