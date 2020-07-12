using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Data.Entity;
using System.Runtime.CompilerServices;

namespace NewsService.Models
{
    public class User
    {
        public int Id { get; set; }
        [DisplayName("User Name")]
        [Required(ErrorMessage ="Please specify a username!")]
        public string Username { get; set; }

        [DataType(DataType.Password)]
        [Required(ErrorMessage = "Please specify a password!")]
        public string Password { get; set; } 
    }

    public class UserDbContext : DbContext
    {
        public DbSet<User> User { get; set; }
    }
}