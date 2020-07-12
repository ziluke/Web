using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace NewsService.Controllers
{
    public class HelloController : Controller
    {
        // GET: Hello
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult Welcome(string name)
        {
            ViewBag.Message = "Hello " + name;
            ViewBag.NumTimes = 5;
            return View();
        }
    }
}