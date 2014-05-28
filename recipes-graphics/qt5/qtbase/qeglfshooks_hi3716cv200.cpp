/****************************************************************************
**
** Copyright (C) 2013 Digia Plc and/or its subsidiary(-ies).
** Contact: http://www.qt-project.org/legal
**
** This file is part of the qmake spec of the Qt Toolkit.
**
** $QT_BEGIN_LICENSE:LGPL$
** Commercial License Usage
** Licensees holding valid commercial Qt licenses may use this file in
** accordance with the commercial license agreement provided with the
** Software or, alternatively, in accordance with the terms contained in
** a written agreement between you and Digia.  For licensing terms and
** conditions see http://qt.digia.com/licensing.  For further information
** use the contact form at http://qt.digia.com/contact-us.
**
** GNU Lesser General Public License Usage
** Alternatively, this file may be used under the terms of the GNU Lesser
** General Public License version 2.1 as published by the Free Software
** Foundation and appearing in the file LICENSE.LGPL included in the
** packaging of this file.  Please review the following information to
** ensure the GNU Lesser General Public License version 2.1 requirements
** will be met: http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html.
**
** In addition, as a special exception, Digia gives you certain additional
** rights.  These rights are described in the Digia Qt LGPL Exception
** version 1.1, included in the file LGPL_EXCEPTION.txt in this package.
**
** GNU General Public License Usage
** Alternatively, this file may be used under the terms of the GNU
** General Public License version 3.0 as published by the Free Software
** Foundation and appearing in the file LICENSE.GPL included in the
** packaging of this file.  Please review the following information to
** ensure the GNU General Public License version 3.0 requirements will be
** met: http://www.gnu.org/copyleft/gpl.html.
**
**
** $QT_END_LICENSE$
**
****************************************************************************/

#include "qeglfshooks.h"

#include <fcntl.h>
#include <unistd.h>
#include <linux/fb.h>
#include <sys/ioctl.h>

#include "EGL/fbdev_window.h"

#include <qpa/qplatformwindow.h>


QT_BEGIN_NAMESPACE

class QEglFSHi3716CV200Hooks : public QEglFSHooks
{
private:
    void hi_fb_initpara();
public:
    virtual void platformInit();
    virtual EGLNativeWindowType createNativeWindow(QPlatformWindow *window, const QSize &size, const QSurfaceFormat &format);
    virtual void destroyNativeWindow(EGLNativeWindowType window);
};

void QEglFSHi3716CV200Hooks::hi_fb_initpara()
{
    int err;
    struct fb_var_screeninfo vinfo;

    int console_fd = open("/dev/fb0", O_RDWR, 0);

    memset(&vinfo, 0, sizeof(vinfo));

    ioctl(console_fd, FBIOGET_VSCREENINFO, &vinfo);
    vinfo.bits_per_pixel   = 32;
    vinfo.red.length       = 8;
    vinfo.green.length     = 8;
    vinfo.blue.length      = 8;
    vinfo.transp.length    = 8;

    vinfo.blue.offset      = 0;
    vinfo.green.offset     = 8;
    vinfo.red.offset       = 16;
    vinfo.transp.offset    = 24;

    vinfo.yres_virtual    = 2 * vinfo.yres;

    if ((err = ioctl(console_fd, FBIOPUT_VSCREENINFO, &vinfo)) < 0)
    {
        qWarning("Unable to set double buffer mode!, err=0x%x\n", err);
    }

    close(console_fd);

    return;
}

void QEglFSHi3716CV200Hooks::platformInit()
{
    QEglFSHooks::platformInit();
    hi_fb_initpara();
}

EGLNativeWindowType QEglFSHi3716CV200Hooks::createNativeWindow(QPlatformWindow *window, const QSize &size, const QSurfaceFormat &format)
{
    fbdev_window *fbwin = (fbdev_window *)malloc( sizeof( fbdev_window ) );
    if ( NULL == fbwin )
    {
            return 0;
    }

    fbwin->width = size.width();
    fbwin->height = size.height();
    return (EGLNativeWindowType)fbwin;
}

void QEglFSHi3716CV200Hooks::destroyNativeWindow(EGLNativeWindowType window)
{
    free(window);
}

QEglFSHi3716CV200Hooks eglFSHi3716CV200Hooks;
QEglFSHooks *platformHooks = &eglFSHi3716CV200Hooks;


QT_END_NAMESPACE
