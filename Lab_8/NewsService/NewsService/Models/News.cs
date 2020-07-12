using System;
using System.Data.Entity;

namespace NewsService.Models
{
    public class News
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public string Producer { get; set; }

        //[DataType(DataType.Date)]
        public DateTime Date { get; set; }
        public string Category { get; set; }
        public string Description { get; set; }
    }

    public class NewsDBContext : DbContext
    {
        public DbSet<News> News { get; set; }
    }
}