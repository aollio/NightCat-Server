import os
from datetime import datetime
from fabric.api import *

# 服务器登录用户
env.user = 'root'
env.sudo_user = 'root'
# 服务器地址
env.hosts = ['106.15.192.136']

# 编译后的文件名称
Tar_File = 'yemao-server-1.0.0.jar'


# 编译项目
def build():
    local('gradle build -x test')

# 放置到Temp中
Remote_Temp_File = '/tmp/%s' % Tar_File
# 测试文件夹
Remote_Dev_Base_Dir = '/root/yemao-dev'
# 正式文件夹
Remote_Prod_Base_Dir = '/root/yemao'


# 部署到测试服务器
def deploy_dev():
    newdir = 'main-%s' % datetime.now().strftime('%y-%m-%d_%H.%M.%S')
    run('rm -rf %s' % Remote_Temp_File)
    with lcd(os.path.join(os.path.abspath('.'), 'build', 'libs')):
        put(Tar_File, Remote_Temp_File)

    with cd(Remote_Dev_Base_Dir):
        run('mkdir %s' % newdir)
    with cd('%s/%s' % (Remote_Dev_Base_Dir, newdir)):
        run('cp %s .' % Remote_Temp_File)
        run('mv %s main.jar' % Tar_File)
        run('touch app.log')

    with cd(Remote_Dev_Base_Dir):
        run('rm -f main')
        run('ln -s %s main' % newdir)
        run('chown root main')
        run('chown -R root %s ' % newdir)
    with settings(warn_only=True):
        run('supervisorctl stop yemao-dev')
        run('supervisorctl start yemao-dev')


# 部署到正式服务器
def deploy_prod():
    newdir = 'main-%s' % datetime.now().strftime('%y-%m-%d_%H.%M.%S')
    run('rm -rf %s' % Remote_Temp_File)
    with lcd(os.path.join(os.path.abspath('.'), 'build', 'libs')):
        put(Tar_File, Remote_Temp_File)

    with cd(Remote_Prod_Base_Dir):
        run('mkdir %s' % newdir)
    with cd('%s/%s' % (Remote_Prod_Base_Dir, newdir)):
        run('cp %s .' % Remote_Temp_File)
        run('mv %s main.jar' % Tar_File)
        run('touch app.log')

    with cd(Remote_Prod_Base_Dir):
        run('rm -f main')
        run('ln -s %s main' % newdir)
        run('chown root main')
        run('chown -R root %s ' % newdir)
    with settings(warn_only=True):
        run('supervisorctl stop yemao')
        run('supervisorctl start yemao')


# 编译并部署到测试
def dev():
    build()
    deploy_dev()


# 编译并部署到正式
def prod():
    build()
    deploy_prod()
