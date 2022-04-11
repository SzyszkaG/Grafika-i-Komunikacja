using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Forms1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            this.Width = 800;
            this.Height = 800;
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            Graphics g = e.Graphics;
            Pen dlugopis = new Pen(Color.Black,5); // maluje zewnatrz
            Pen dlugopis1 = new Pen(Color.Red,8);
            Brush mojDlugopis2 = new SolidBrush(Color.Black);
            Brush mojDlugopis3 = new SolidBrush(Color.Yellow);
            Brush mojDlugopis = new SolidBrush(Color.Red); // maluje wewnatrz
            Brush mojDlugopis4 = new SolidBrush(Color.White);
            Brush mojDlugopis5 = new SolidBrush(Color.Green);


            Point[] punkty = new Point[3];
            punkty[0] = new Point(400, 475);
            punkty[1] = new Point(600,  550);
            punkty[2] = new Point(200, 550);


            g.FillRectangle(mojDlugopis5, 0, 700, 800, 100);
            g.FillPolygon(mojDlugopis2, punkty);
            g.FillRectangle(mojDlugopis, 220,550,360,220);
            g.FillEllipse(mojDlugopis3, 600, 100, 100, 100);
            g.FillRectangle(mojDlugopis4, 250, 575, 100, 75);
            g.FillRectangle(mojDlugopis2, 450, 600, 100, 160);
            g.FillEllipse(mojDlugopis4, 100, 600, 30, 30);
            g.FillEllipse(mojDlugopis2, 100, 630, 30, 50);
            g.DrawLine(dlugopis, 105, 670, 95, 720);
            g.DrawLine(dlugopis, 125, 670, 125, 720);
            g.DrawLine(dlugopis, 110, 635, 85, 680);
            g.DrawLine(dlugopis, 120, 635, 140, 680);

        }
    }
}
